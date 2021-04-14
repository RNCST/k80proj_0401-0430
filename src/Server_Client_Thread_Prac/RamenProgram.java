package Server_Client_Thread_Prac;

public class RamenProgram {

	// run Debug
	public static void main(String[] args) {
		try {
			RamenCook ramenCook = new RamenCook(Integer.parseInt(args));
			new Thread(ramenCook, "personA").start();
			new Thread(ramenCook, "personB").start();
			new Thread(ramenCook, "personC").start();
			new Thread(ramenCook, "personD").start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class RamenCook implements Runnable {
	
	private int ramenCount;
	private String[] burners = { "_", "_", "_ ", "_", };

	public RamenCook(int count) {
		ramenCount = count;
	}

	@Override
	public void run() {
		while (ramenCount > 0) {
			
			synchronized (this) {
				ramenCount--;
				System.out.println(
						Thread.currentThread().getName() + 
			            ":" + ramenCount + "개 남음");
			}

			for (int i = 0; i < burners.length; i++) {
				if (!burners[i].equals("_")) continue;

				synchronized (this) {
					burners[i] = Thread.currentThread().getName();
					System.out.println(
							"                  " 
					        + Thread.currentThread().getName()
							+ ": [" + (i+1) + "]번 버너 ON");
					showBurner();
				}
				
				try {
					Thread.sleep(5000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				synchronized (this) {
					burners[i] = "_";
					System.out.println(
							"                  " + Thread.currentThread().getName() 
							+ ": [" + (i+1) + "]번 버너 OFF");
					showBurner();
				}
				break;
			}
			
			try {
				Thread.sleep(Math.round(1000 * Math.random()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

	private void showBurner() {
		String stringToPrint
		= "                                              ";
		for (int i = 0; i < burners.length; i++) {
			stringToPrint += (" " + burners[i]);
		}
		System.out.println(stringToPrint);

	}
}
