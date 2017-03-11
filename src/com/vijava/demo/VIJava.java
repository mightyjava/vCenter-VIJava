package com.vijava.demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.vmware.vim25.VirtualMachineQuickStats;
import com.vmware.vim25.VirtualMachineSummary;
import com.vmware.vim25.mo.ClusterComputeResource;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.HostSystem;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.VirtualMachine;

public class VIJava {
	private ServiceInstance serviceInstance = null;

	public VIJava(String url, String userName, String password) {
		try {
			serviceInstance = new ServiceInstance(new URL(url), userName, password, true);
		} catch (RemoteException | MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	private ManagedEntity[] getClusters() {
		Folder rootFolder = null;
		ManagedEntity[] entities = null;
		rootFolder = serviceInstance.getRootFolder();
		try {
			entities = new InventoryNavigator(rootFolder).searchManagedEntities("ClusterComputeResource");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return entities;
	}
	
	public List<VirtualMachineDTO> findVirtualMachines() {
		List<VirtualMachineDTO> machineDTOs = new ArrayList<VirtualMachineDTO>();
		ManagedEntity[] entities = getClusters();
		ClusterComputeResource resource = null;
		if(entities.length > 0) {
			for(ManagedEntity entity : entities) {
				resource = (ClusterComputeResource) entity;
				HostSystem[] hostSystems = resource.getHosts();
				if(hostSystems.length > 0) {
					for(HostSystem hostSystem : hostSystems) {
						VirtualMachine [] virtualMachines = null;
						try {
							virtualMachines = hostSystem.getVms();
							for(VirtualMachine virtualMachine : virtualMachines) {
								VirtualMachineDTO machineDTO = new VirtualMachineDTO();
								machineDTO.setName(virtualMachine.getName());
								machineDTO.setStatus(virtualMachine.getOverallStatus().name());
								machineDTO.setState(virtualMachine.getSummary().getRuntime().getPowerState().name());
								
								VirtualMachineSummary summary = virtualMachine.getSummary();
								Double comittedSpace = Double.valueOf(summary.getStorage().getCommitted())/1024/1024/1024;
								Double unComittedSpace = Double.valueOf(summary.getStorage().getUncommitted())/1024/1024/1024;
								machineDTO.setProvisionedSpace(comittedSpace + unComittedSpace);
								machineDTO.setUsedSpace(comittedSpace);
								
								VirtualMachineQuickStats quickStats = summary.getQuickStats();
								machineDTO.setHostCPU(quickStats.getOverallCpuUsage());
								machineDTO.setHostMemory(quickStats.getHostMemoryUsage());
								machineDTOs.add(machineDTO);
							} 
						} catch (RemoteException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return machineDTOs;
	}
}




































