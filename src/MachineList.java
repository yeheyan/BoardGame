import listadt.ListImp;

public class MachineList{
    private ListImp<Machine> machineList;

    public MachineList(){
        machineList = new ListImp<Machine>();
    }
    public MachineList(ListImp<Machine> machineList){
        this.machineList = machineList;
    }

    public void addMachine(Machine machine){
        machineList.add(machine);
    }

    public void removeMachine(Machine machine){
        machineList.remove(machine);
    }

    //getter
    public Machine getMachine(int index){
        return machineList.get(index);
    }

    public int size(){
        return machineList.count(x -> true);
    }

    public ListImp<Machine> getMachineList(){
        return machineList;
    }

	public void setMachines(ListImp<Machine> machineList) {
		this.machineList = machineList;
	}

    public String toString(){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < machineList.count(x -> true); i++){
            result.append(machineList.get(i).getMachine()).append("\n");
        }
        return result.toString();
    }
}
