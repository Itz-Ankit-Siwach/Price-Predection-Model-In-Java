import java.util.*;

class Item {
    private String itemName;
    private String timeDuration;
    private int[] itemList;
    private int totalDuration;
    private double predictedPrice;

    public void getItemData() {
        Scanner scanner = new Scanner(System.in);

        // Prompt and store item name
        System.out.print("Item name: ");
        itemName = scanner.next();

        // Prompt and store time duration format
        System.out.print("Enter time-duration ('years/months/days'): ");
        timeDuration = scanner.next();

        // Prompt and parse duration input
        if (timeDuration.equalsIgnoreCase("days") || timeDuration.equalsIgnoreCase("months")) {
            System.out.print("Enter total duration in " + timeDuration + ": ");
            totalDuration = scanner.nextInt();
        } else {
            System.out.print("Enter years range (e.g., 2011-2020): ");
            String yearRange = scanner.next();
            String[] years = yearRange.split("-");
            int startYear = Integer.parseInt(years[0]);
            int endYear = Integer.parseInt(years[1]);
            totalDuration = endYear - startYear + 1;
        }

        itemList = new int[totalDuration];

        // Prompt and store price data for each duration
        System.out.print("Enter " + totalDuration + " " + timeDuration +
                " price data of " + itemName + "[]: ");

        for (int i = 0; i < totalDuration; i++) {
            itemList[i] = scanner.nextInt();
        }

        System.out.println("+++-----------------------------------------------------------------------+++");
    }

    public int predictionEvaluation1() {
        int td = totalDuration - 1;

        // Check if prices are consistently increasing or decreasing
        if (td >= 4 && itemList[td] > itemList[td - 1]
                && itemList[td - 1] > itemList[td - 2]
                && itemList[td - 2] > itemList[td - 3]
                && itemList[td - 3] > itemList[td - 4]) {

            // Predict price based on trend
            predictedPrice = itemList[td] + (itemList[td] - itemList[td - 1]) - (itemList[td - 1] - itemList[td - 2]) / 5.0;
            return 1; // Return prediction success
        } else if (td >= 4 && itemList[td] < itemList[td - 1]
                && itemList[td - 1] < itemList[td - 2]
                && itemList[td - 2] < itemList[td - 3]
                && itemList[td - 3] < itemList[td - 4]) {

            // Predict price based on decreasing trend
            predictedPrice = itemList[td] - (itemList[td - 1] - itemList[td]) - (itemList[td - 1] - itemList[td]) / 5.0;
            return 1; // Return prediction success
        } else {
            return 0; // Return prediction failure
        }
    }

    public void predictionEvaluation2() {
        int tot1 = 0, tot2 = 0, tot3 = 0;

        for (int x = 0; x < totalDuration / 2; x++) {
            tot1 += itemList[x];
        }

        for (int y = totalDuration / 2; y < totalDuration; y++) {
            tot2 += itemList[y];
        }

        if (tot1 > tot2) {
            int tot = tot1 - tot2;
            predictedPrice = (tot2 * 2) / totalDuration + (tot / totalDuration) * 7 / 10.0;
        } else if (tot2 > tot1) {
            int tot = tot2 - tot1;
            predictedPrice = (tot1 * 2) / totalDuration + (tot / totalDuration) * 7 / 10.0;
        } else {
            for (int z = 0; z < totalDuration; z++) {
                tot3 += itemList[z];
            }
            predictedPrice = tot3 / (double) totalDuration;
        }
    }

    public void printPredictedData() {
        System.out.print("Price Data: ");
        for (int l = 0; l < totalDuration; l++) {
            System.out.print(itemList[l] + " ");
        }
        System.out.println("| Predicted Data: " + predictedPrice);
    }
}

public class PricePredictionModel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Item[] itemAdd = new Item[50];

        int noOfItem;

        // Prompt for the number of items
        System.out.print("Number of items you want to predict the price for: ");
        noOfItem = scanner.nextInt();

        // Input item data and perform predictions
        for (int j = 0; j < noOfItem; j++) {
            System.out.print("Enter data for item " + (j + 1) + "\n");
            itemAdd[j] = new Item();
            itemAdd[j].getItemData();
        }
        System.out.println("--------------------------------------------------------------------------------------------------+");

        // Display predicted data for each item
        for (int k = 0; k < noOfItem; k++) {
            if (itemAdd[k].predictionEvaluation1() == 1) {
                itemAdd[k].printPredictedData();
            } else {
                itemAdd[k].predictionEvaluation2();
                itemAdd[k].printPredictedData();
            }
        }
        System.out.println("--------------------------------------------------------------------------------------------------+");
    }
}
