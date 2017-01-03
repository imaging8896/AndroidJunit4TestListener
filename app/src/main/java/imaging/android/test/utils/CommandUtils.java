package imaging.android.test.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandUtils {

	public static String execShell(String shellCmd) {
		String[] cmd = {
				"/system/bin/sh",
				"-c",
				shellCmd
		};
		return exec(cmd);
	}

	public static String exec(String cmd) {
		try {
			Process process = Runtime.getRuntime().exec(cmd);
			return getCommandResult(process);
		} catch (Exception e) {
		    throw new RuntimeException(e);
		}
	}

	public static String exec(String[] cmd) {
		try {
			Process process = Runtime.getRuntime().exec(cmd);
			return getCommandResult(process);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static String getCommandResult(Process cmdProcess) throws IOException, InterruptedException {
		StringBuilder output = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(cmdProcess.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			output.append(line).append("\n");
			cmdProcess.waitFor();
		}
		return output.toString();
	}
}
