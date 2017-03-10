package dds.poi.test.builder;

import dds.poi.builder.Builder;
import dds.poi.model.search.Terminal;

public class TerminalBuilder extends Builder<Terminal> {

	private Terminal terminal = new Terminal();

	public TerminalBuilder id(int idTerminal) {
		this.terminal.setIdTerminal(idTerminal);
		return this;
	}

	@Override
	public boolean isValidBuild() {
		return this.terminal.getIdTerminal() > 0;
	}

	@Override
	public Terminal returnBuildObject() {
		return this.terminal;
	}

}
