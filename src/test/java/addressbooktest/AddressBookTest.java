package addressbooktest;

import java.time.LocalDate;
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
		System.out.println("UC-17 completed");
	}

	//retrieve the data from db using date range
	@Test
	public void givenDateRange_WhenRetrieved_ShouldMatchTheEmployeeCount() {
		AddressBookService addressBookService = new AddressBookService();
		addressBookService.readContactData(AddressBookService.IOService.DB_IO);
		LocalDate startDate = LocalDate.of(2017, 05, 20);
		LocalDate endDate = LocalDate.now();
		List<PersonInformation> contactData = addressBookService.readContactForDateRange(AddressBookService.IOService.DB_IO, startDate, endDate);
		Assert.assertEquals(2, contactData.size());
		System.out.println("UC-18 completed");
	}
	
	//retrieve data from db from particular city
    @Test
    public void givenCityNameInDB_WhenRetrieved_ShouldMatchPersonCount() {
    	AddressBookService addressBookService = new AddressBookService();
		addressBookService.readContactData(AddressBookService.IOService.DB_IO);
        List<PersonInformation> personInfoData = addressBookService.readContactForParticularCity(AddressBookService.IOService.DB_IO, "Mumbai");
        Assert.assertEquals(2, personInfoData.size());
    }
    
	//retrieve data from db from particular state
	@Test
    public void givenStateNameInDB_WhenRetrieved_ShouldMatchPersonCount() {
		AddressBookService addressBookService = new AddressBookService();
		addressBookService.readContactData(AddressBookService.IOService.DB_IO);
        List<PersonInformation> personInfoData = addressBookService.readContactForParticularState(AddressBookService.IOService.DB_IO, "Maharashtra");
        Assert.assertEquals(2, personInfoData.size());
    }
}
