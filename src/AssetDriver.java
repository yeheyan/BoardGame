import listadt.ListImp;

public class AssetDriver {
    public static void main(String[] args) {
        Model model = new Model("Example Model");
        model.load();
        MachineList machineList = model.getMachineList();
        System.out.println("Full machine list from file: \n" + machineList + "\n");
        // map the machine list to a list of their Economy points
        ListImp<Integer> economyPoints = machineList.getMachineList().map(x -> x.getEconomyPoints());
        System.out.println("Machines Econ points list: \n" + economyPoints + "\n");
        // filter the machine list to only include machines with more than 5 Science points
        ListImp<Machine> highScienceMachines = machineList.getMachineList().filter(x -> x.getSciencePoints() > 5);
        System.out.println("Machines with science points higher than 5: \n" + highScienceMachines.map(x -> x.getMachine()) + "\n");
        //count the number of machines with more than 5 Science points
        int count = machineList.getMachineList().count(x -> x.getSciencePoints() > 5);
        System.out.println("Number of machines with science points higher than 5: " + count + "\n");
    }
}
