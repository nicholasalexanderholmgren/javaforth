package edu.mccc.cos210.ds.fp.javaforth.controlwords;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;

public class Leave extends ForthWordBase {
	public Leave() {
		super("LEAVE", "Force  termination  of a DO-LOOP at the next LOOP or +LOOP  by setting  the  loop  limit equal to the current  value  of  the index.   The  index itself remains  unchanged,  and  execution proceeds   normally   until  the  loop  terminating  word   is encountered.", true);
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary, ITerminalOutput terminalOutput) {
		throw new LeaveLoopException();
	}
}
