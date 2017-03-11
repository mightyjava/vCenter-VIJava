package com.vijava.demo;

public class VirtualMachineDTO {
	private String name;
	private String status;
	private String state;
	private Integer hostCPU;
	private Integer hostMemory;
	private Double provisionedSpace;
	private Double usedSpace;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getHostCPU() {
		return hostCPU;
	}

	public void setHostCPU(Integer hostCPU) {
		this.hostCPU = hostCPU;
	}

	public Integer getHostMemory() {
		return hostMemory;
	}

	public void setHostMemory(Integer hostMemory) {
		this.hostMemory = hostMemory;
	}

	public Double getProvisionedSpace() {
		return provisionedSpace;
	}

	public void setProvisionedSpace(Double provisionedSpace) {
		this.provisionedSpace = provisionedSpace;
	}

	public Double getUsedSpace() {
		return usedSpace;
	}

	public void setUsedSpace(Double usedSpace) {
		this.usedSpace = usedSpace;
	}

	@Override
	public String toString() {
		return "\n[name=" + name + ", status=" + status + ", state=" + state + ", hostCPU=" + hostCPU
				+ ", hostMemory=" + hostMemory + ", provisionedSpace=" + provisionedSpace + ", usedSpace=" + usedSpace
				+ "]";
	}

}
