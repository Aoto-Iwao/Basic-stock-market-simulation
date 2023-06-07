package unisa.dse.a2.students;

/**
 * @author Aoto Iwao
 * @studentID: 110355679
 * @email: iwaay002@mymail.unisa.edu.au>
 */

public class UntradedCompanyException extends Exception
{
	public UntradedCompanyException(String companyCode)
	{
		super("The company " + companyCode + " is not listed on this exchange.");
	}
}
