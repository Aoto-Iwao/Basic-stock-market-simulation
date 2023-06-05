package unisa.dse.a2.students;

public class UntradedCompanyException extends Exception
{
	public UntradedCompanyException(String companyCode)
	{
		super("The company " + companyCode + " is not listed on this exchange.");
	}
}
