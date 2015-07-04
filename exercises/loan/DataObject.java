package exercises.loan;


abstract class DataObject {

	abstract boolean isValid();
	abstract void printErrorMsg();

	public final boolean validate() {
		if (notValid()) {
			printErrorMsg();
			return false;
		}
		return true;
	}

	public final boolean notValid() {
		return !isValid();
	}
}

