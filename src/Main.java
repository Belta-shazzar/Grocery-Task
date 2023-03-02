import java.util.Scanner;

import static java.lang.System.out;

public class Main {

    static Grocery grocery;
    public static void main(String[] args) {

        Scanner input =new Scanner(System.in);

        byte tries = 3;

        do {
            try {
                out.print("Enter name of name of item you are purchasing: ");
                grocery = new Grocery(input.nextLine());

                out.print("Enter price of item as two numbers, i.e 5 2.99: ");
                String packPrice = input.nextLine();

                String[] packPToArr = packPrice.split(" ");

                int groupCount = Integer.parseInt(packPToArr[0]);
                double groupPrice = Double.parseDouble(packPToArr[1]);

                out.print("Enter number of items purchased: ");
                short qty = input.nextShort();

                if (qty > groupPrice) {
                    out.println("Quantity cannot be greater than group price");
                    throw new RuntimeException();
                }

                tries = 0;

                processItem(groupCount, Math.round(groupPrice * 100.0) / 100.0, qty);
                display();
            } catch (RuntimeException ex) {
                tries -= 1;
                out.println("Invalid input!, try again \n" + tries + " tries left");
            }
        } while (tries != 0);
    }

    public static void processItem(int groupCount, double groupPrice, short qty) {
        double unitCost = groupPrice / groupCount;
        double totalCost = unitCost * qty;

        grocery.setUnitCost(Math.round(unitCost * 100.0) / 100.0);
        grocery.setTotalCost(Math.round(totalCost * 100.0) / 100.0);
    }

    public static void display() {
        out.println();
        out.println("You bought: " + grocery.getNameOfItem());
        out.println("Cost each: $" + grocery.getUnitCost());
        out.println("Total cost: $" + grocery.getTotalCost());
    }
}