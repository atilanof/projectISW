package icai.dtc.isw.message;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Message implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String context;
	private Map<String, Object> input;
	private Map<String, Object> output;
	
	public Message () {
		setContext(new String());
		input=new HashMap<String, Object>();
		output=new HashMap<String, Object>();
		
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Map<String, Object> getInputs() {
		return input;
	}

	public void setInputs(Map<String, Object> input) {
		this.input = input;
	}

	public Map<String, Object> getOutput() {
		return output;
	}

	public void setOutput(Map<String, Object> output) {
		this.output = output;
	}
	
	
}
