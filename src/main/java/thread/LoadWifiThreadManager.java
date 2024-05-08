package thread;

// observer
public class LoadWifiThreadManager {
	static LoadWifiInfoThread thread;
	static int cur_total = -1;
	static int total = -1;
	
	public static int getCurTotal()
	{	
		return cur_total; // 마지막 cur_total 값 반환, 스레드가 시작된 적이 없는 경우 -1 반환
	}
	
	public static void setCurTotal(int tot)
	{
		cur_total = tot;
	}
	
	public static int getTotal()
	{
		return total; // 마지막 total 값 반환, 스레드가 시작된 적이 없는 경우 -1 반환
	}
	
	public static void setTotal(int tot)
	{
		total = tot;
	}
	
	public static boolean addThread()
	{
		if(thread != null) return false;
		else
		{
			thread = new LoadWifiInfoThread();
			return true;
		}
	}
	
	public static void run()
	{
		thread.start();
	}
	
	public static void remove()
	{
		thread = null;
	}
}
