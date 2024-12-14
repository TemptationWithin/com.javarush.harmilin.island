package handler;

public class Timer {

    public static void sleep(long mils){
        try {
            Thread.sleep(mils);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void funnySteps(){
        for (int i = 0; i < 10; i++) {
            sleep(200);
            System.out.print("🐾");
        }
        System.out.print("\n");
    }
    public static void funnyPreparing(){
        System.out.print("Wolves sharpen their teeth...");
        sleep(500);
        System.out.print("🐺");
        sleep(500);
        System.out.print("🦷 ");
        sleep(400);
        funnySteps();
        sleep(500);

        System.out.print("Ducks flying in from the south...");
        sleep(500);
        System.out.print("🦆");
        sleep(500);
        System.out.print("<<<<🦆🦆🦆...");
        sleep(400);
        System.out.print("<<<<🦆🦆🦆..." + "🌅\n");
        sleep(500);

        System.out.print("Bears emerging from their dens...");
        sleep(500);
        System.out.print("🐻");
        sleep(500);
        System.out.print("🌞" +"🕳️....");
        sleep(400);
        funnySteps();
        sleep(500);

        System.out.print("Deer come out to feed...");
        sleep(500);
        System.out.print("🦌");
        sleep(500);
        System.out.print("🌿"+"🌿"+"<<🦌🦌🦌<"+"🌿"+"🌲"+"<🦌...");
        sleep(400);
        System.out.print("🌿"+"🌿"+"<<🦌🦌<"+"🌿"+"🌲"+"<🦌🦌...\n");
        sleep(500);

        System.out.print("Foxes dyes their fur red...");
        sleep(500);
        System.out.print("🦊");
        sleep(500);
        System.out.print("💅 " + " 💅");
        sleep(400);
        funnySteps();
        sleep(500);

        System.out.print("Plants are pushing through the ground...\n");
        sleep(500);
        System.out.print("🌱"+"🌱"+"🌱"+"🌱"+"🌱"+"🌱\n");
        sleep(500);
        System.out.print("🌳"+"🌲"+"🌳"+"🌲"+"🌳"+"🌲\n");
        sleep(400);
        System.out.print("🌲"+"🌳"+"🌲"+"🌳"+"🌲"+"🌳\n");
        sleep(500);
        System.out.print("🌾"+"🌻"+"🌾"+"🌻"+"🌻"+"🌾\n");
        sleep(500);
        System.out.print("🌳"+"🌲"+"🌳"+"🌲"+"🌳"+"🌲\n");
        sleep(400);
        System.out.print("🌲"+"🌳"+"🌲"+"🌳"+"🌲"+"🌳\n");
        sleep(500);
    }

}
