package simplemessenger;

public class Controller {
	private View _view;
	private Model _model;
	private ConnectionStatus _connectionStatus;
	
		
	Controller(View view, Model model){
		_view=view;
		_model=model;
	}
	
	private void ReadSettings(){
		
	}
	
	public ConnectionStatus getConnectionStatus(){
		return _connectionStatus;
	}
	
	public void setConnectionStatus(ConnectionStatus c){
		_connectionStatus=c;
	}
	
	public void connect(){
		//TODO
		_connectionStatus = ConnectionStatus.CONNECTING;
		_connectionStatus = ConnectionStatus.CONNECTED;
	}
	
	public void disconnect(){
		//TODO
		
		_connectionStatus =  ConnectionStatus.NOTCONNECTED;
	}
	
	public void addFriend(String userid){
		
	}
	
	public void deleteFriend(String userid){
		
	}
	
	

}
