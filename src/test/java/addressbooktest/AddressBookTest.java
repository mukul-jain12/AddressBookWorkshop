package addressbooktest;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import model.PersonInformation;
import service.AddressBookService;

public class AddressBookTest {
	@Test
    public void givenContactsInDB_MatchEmployeeCount() {
		AddressBookService addressBookService = new AddressBookService();
        List<PersonInformation> contactData = addressBookService.readPersonInfoData(AddressBookService.IOService.DB_IO);
        System.out.println(contactData);
        Assert.assertEquals(3, contactData.size());
    }
}
