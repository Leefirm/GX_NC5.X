package nc.test;

import nc.vo.pub.guid.UUID;
import nc.vo.pub.guid.UUIDGenerator;

public class TestUUID {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		for (int i = 0; i < 1000; i++) {
//			String uuid = UUIDGenerator.getInstance().generateRandomBasedUUID().toString();
			String uuid = new UUID().toString();
			System.out.println(uuid);
		}
	}

}
