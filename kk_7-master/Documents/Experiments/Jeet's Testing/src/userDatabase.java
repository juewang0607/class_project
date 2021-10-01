import java.util.*;

public class userDatabase {
	private ArrayList<testUser> userList;
	
	public void delUser(testUser user) throws userNotFoundException {
		int check = 1;
		for(int x = 0; x<userList.size();x++) {
			if(userList.get(x).getEmail().contentEquals(user.getEmail())) {
				userList.remove(x);
				check = 1;
				break;
				}
			else {
				check = 0;
				}
			}
		if(check == 0) {
			throw new userNotFoundException("The specified user was not found");
			}
		}
	
	public void addUser(testUser user) throws userExistsException {
		int check = 1;
		for(int x = 0; x<userList.size();x++) {
			if(userList.get(x).getEmail().contentEquals(user.getEmail())) {
				check = 0;
				throw new userExistsException("There is already an account with this email");
				}
			}
		if(check == 1) {
			userList.add(user);
			}
		}
	
	public testUser findUser(testUser user) throws userExistsException {
		for(int x = 0; x<userList.size();x++) {
			if(userList.get(x).getEmail().contentEquals(user.getEmail())) {
				return userList.get(x);
				}
			}
		throw new userExistsException("There is already an account with this email");
		}
	}
