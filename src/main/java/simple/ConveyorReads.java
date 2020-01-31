package simple;

import  java.util.ArrayList;

import java.util.List;


public class ConveyorReads {
	    private String name;
	    private Long time ;
	    private String action;
		public static List<ConveyorReads> conveyors = new ArrayList<ConveyorReads>();

	    public ConveyorReads(String name, Long time, String action) {
	        this.name = name;
	        this.time  = time;
	        this.action = action;
	        //conveyors.add(this);
	        	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public Long getTime() {
	        return time;
	    }

	    public void setTime(Long time) {
	        this.time =(Long) time;
	    }

	    public String getAction() {
	        return action;
	    }

	    public void setAction(String action) {
	        this.action = action;
	    }

		public static ConveyorReads createConveyor(String[] metadata) {
		String name = metadata[0];
		//int price = Integer.parseInt(metadata[1]);
		String action = metadata[1];
		Long time =  Long.decode(metadata[2]);


		// create and return book of this metadata
		return new ConveyorReads(name, time, action);
	}

	    public List conveyorsObjects(){
	    	return conveyors;
		}


	    @Override
	    public String toString() {
	        return "IndividualConveyor [name=" + name + ", Time=" + time + ", Action=" + action
	                + "]";
	    }

	}


