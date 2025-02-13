public enum OutputType {

	FILE {
		@Override
		public Output getInstance(SystemWrapper systemWrapper) {
			return new OutputToFile();
		}
	},
	CONSOLE

	{

		@Override
		public Output getInstance(SystemWrapper systemWrapper) {
			return new OutputToConsole(systemWrapper);
		}

	};

	public abstract Output getInstance(SystemWrapper systemWrapper);

}