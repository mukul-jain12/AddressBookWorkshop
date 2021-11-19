package addressbooktest;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import model.PersonInformation;
import service.AddressBookService;

public class AddressBookTest {

	//Match database data count to expected data 
	@Test
	public void givenContactsInDB_MatchEmployeeCount() {
		AddressBookService addressBookService = new AddressBookService();
		List<PersonInformation> contactData = addressBookService.readContactData(AddressBookService.IOService.DB_IO);
		Assert.assertEquals(3, contactData.size());
		System.out.println("UC-16 completed");
	}

	//update the data in db and also check sync
	@Test
	public void givenNewStateForPerson_WhenUpdated_ShouldSyncWithDB() {
		AddressBookService addressBookService = new AddressBookService();
		addressBookService.readContactData(AddressBookService.IOService.DB_IO);
		addressBookService.updatePersonInfo("Raju", "Maharashtra");
		boolean result = addressBookService.checkContactInSyncWithDB("Raju");
		Assert.assertTrue(result);
		System.out.println("UC-16 completed");
	}
}
