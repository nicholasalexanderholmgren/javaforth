package edu.mccc.cos210.ds.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class CheckNet {
	private static String SERVER_ADDR = "localhost";
	public static void main(String... args) {
		if (args.length == 1) {
			SERVER_ADDR = args[0];
		}
		CheckNet cn = new CheckNet();
		if ("localhost".equals(CheckNet.SERVER_ADDR)) {
			new Thread(CheckNet::beServer).start();
		}
		cn.beClient();
	}
	private static void beServer() {
		DatagramSocket sock;
		byte[] buffer = new byte[256];
		DatagramPacket pkt = new DatagramPacket(buffer, buffer.length);
		try {
			sock = new DatagramSocket(5972);
			while (true) {
				sock.receive(pkt);
				String message = new String(buffer, 0, pkt.getLength());
				System.out.println(pkt.getAddress().toString().substring(1) + ": " + message);
				pkt.setLength(buffer.length);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	private void beClient() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String server_address = CheckNet.SERVER_ADDR;
			int server_port = 5972;
			InetAddress address = InetAddress.getByName(server_address);
			while (true) {
				byte[] buffer = br.readLine().getBytes();
				DatagramPacket pkt = new DatagramPacket(buffer, buffer.length, address, server_port);
				DatagramSocket sock = new DatagramSocket();
				sock.send(pkt);
				sock.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
