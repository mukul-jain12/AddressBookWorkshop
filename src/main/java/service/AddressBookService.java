package service;

import java.util.List;

import model.PersonInformation;

public class AddressBookService {
	private AddressBookDBService addressBookDBService;
	private List<PersonInformation> contactList;

	public enum IOService {DB_IO}

	public AddressBookService() {
		addressBookDBService = AddressBookDBService.getInstance();
	}

	public List<PersonInformation> readPersonInfoData(IOService ioService) {
		if(ioService.equals(IOService.DB_IO))
			this.contactList = addressBookDBService.readData();
		return this.contactList;
	}
}
