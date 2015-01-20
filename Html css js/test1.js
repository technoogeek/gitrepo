var bob = {
    firstName: "Bob",
    lastName: "Jones",
    phoneNumber: "(650) 777-7777",
    email: "bob.jones@example.com"
};

var mary = {
    firstName: "Mary",
    lastName: "Johnsonn",
    phoneNumber: "(650) 888-8888",
    email: "mary.johnson@example.com"
};

var contacts = [bob, mary];

contacts[contacts.length] = {
    firstName: "Mary",
    lastName: "Joness",
    phoneNumber: "(650) 888-8888",
    email: "mary.johnson@example.com"
};

function printPerson(person) {
    console.log(person.firstName + " " + person.lastName);
}

function list(contactList) {
	var contactsLength = contactList.length;
	for (var i = 0; i < contactList.length; i++) {
		printPerson(contacts[i]);
	}
}

list(contacts);

/*Create a search function
then call it passing "Jones"*/

function search(contactList,lastName){
    var searchInd = 0;
    for (var i=0; i < contactList.length ; i++){
        if (contactList[i].lastName === lastName){
            console.log("we've found " + 
            contactList[i].firstName + " " +
            contactList[i].lastName);
            searchInd++;
        };
    }
    
    if (!(searchInd > 0)){
        console.log("No Person as " + lastName + " found.");
    }
}

search(contacts,"Joness");