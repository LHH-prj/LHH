package Login.service;

import javafx.event.ActionEvent;

public interface CommonServiceInter {
	public void WindowClose(ActionEvent event);
	public void ErrorMsg(String title, String headerString, String contentText);
	public void ErrorMsg(String headerString, String contentText);
	public void ErrorMsg(String contentText);
}
