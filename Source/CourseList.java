// -----------------------------------------------------
// Assignment 4
// Written by: James Molinari (27801866)
// ----------------------------------------------------

import java.util.NoSuchElementException;

class CourseList {
    
	class CourseNode {
        private Course course;
        private CourseNode next;

        public CourseNode() {
            course = null;
            next = null;
        }

        public CourseNode(Course c, CourseNode next) {
            this.course = c;
            this.next = next;
        }
        
        //copy constructor
        public CourseNode(CourseNode c) {
            this.course = c.course;
            this.next = null;
        }
        
        public CourseNode clone() {
            return new CourseNode(this);
        }

        public Course getCourse() {
            return course;
        }

        public void setCourse(Course course) {
            this.course = course;
        }

        public CourseNode getNext() {
            return next;
        }

        public void setNext(CourseNode next) {
            this.next = next;
        }
    }

    private CourseNode head;
    private int size;

    public CourseList() {
        head = null;
        size = 0;
    }

    public CourseList(CourseList list) {
        addToStart(list.getHead().getCourse());

        if(list.getSize() != 1) {
            CourseNode node = list.getHead().getNext();

            while(node != null) {
                insertAtIndex(node.getCourse(), size);              
                node = node.next;
            }
        }
    }

    public CourseNode getHead() {
        return head;
    }

    public int getSize() {
        return size;
    }

    public void addToStart(Course c) {
        CourseNode node = new CourseNode(c, head);
        head = node;
        size++;
    }
    
    public void append(Course c){
		if (head == null)
		{
			CourseNode temp = new CourseNode(c, null);
			head = temp;
			head.next = null;
		}
		else
		{
			int index = 0;
	        CourseNode start = head;
	        while(start.next != null) {
	            start = start.next;
	            index++;
	        }
	        start.next = new CourseNode(c, null);
			
		}
		size++;

    }

    public void insertAtIndex(Course c, int index) {
        if(index < 0 || index > size) {
            throw new NoSuchElementException();
        }
        
        if(index == 0) {
            addToStart(c);
            return;
        }
        index--;

        CourseNode start = head;
        while(index != 0) {
            start = start.next;
            index--;
        }
        CourseNode node = new CourseNode(c, start.next);
        start.next = node;
        size++;
    }

    public void deleteFromIndex(int index) {
        if(index < 0 || index >= size) {
            throw new NoSuchElementException();
        }
        if(index == 0) {
            deleteFromStart();
            return;
        }
        index--;
        CourseNode start = head;
        while(index != 0) {
            start = start.next;
            index--;
        }
        start.next = start.next.next;       
        size--;
    }

    public void deleteFromStart() {
        if(head != null) {
            head = head.next;
            size--;
        }
    }
    
    public void replaceAtIndex(Course c, int index){
    	if(index < 0 || index > size-1) {
    		return;
        }
    	
    	if (index==0 && head!=null && head.next==null){
    		head = new CourseNode(c, null);
    		return;
    	}
    	
    	if (index==0 && head!=null && head.next!=null){
    		head = new CourseNode(c, head.next);
    		return;
    	}
    	
    	if (index == size-1){
    		int i = 0;
     		CourseNode temp = head;
     		while(i != index-1)
     		{
     			temp = temp.next;
     			i++;
     		}
     		temp.next = new CourseNode(c, null);
     		return;
    	}
    	
    	int i = 0;
 		CourseNode temp = head;
 		while(i != index-1)
 		{
 			temp = temp.next;
 			i++;
 		}
 		temp.next = new CourseNode(c, temp.next.next);
    }
    
    //The find method with cause a privacy leak because since this method returns the pointer to the object, anyone can manipulate any of the contents or even delete it and everything that follows.
    public CourseNode find(String courseID){
    	if (size == 0)
    		return null;
    	else{
    		int i = 0;
    		int ctr = 0;
     		CourseNode temp = head;
     		
     		while(i != size)
     		{
     			ctr++;
     			if (temp.getCourse().getCourseID().equals(courseID)){
     				//System.out.println("Iterations: " + ctr);
     				return temp;
     			}
     			//System.out.println(temp.getCourse().getCourseID());
     			temp = temp.next;
     			i++;
     		}
     		
     		//System.out.println("Iterations: " + ctr);
     		//System.out.println("Not on the list.");
     		return null;
    	}
    }
    
    
    public CourseNode findIT(String courseID){
    	if (size == 0)
    		return null;
    	else{
    		int i = 0;
    		int ctr = 0;
     		CourseNode temp = head;
     		
     		while(i != size)
     		{
     			ctr++;
     			if (temp.getCourse().getCourseID().equals(courseID)){
     				System.out.println(temp.getCourse());
     				System.out.println("Iterations: " + ctr);
     				return temp;
     			}
     			//System.out.println(temp.getCourse());
     			temp = temp.next;
     			i++;
     		}
     		
     		System.out.println("Iterations: " + ctr);
     		System.out.println("Not on the list.");
     		return head; //<--- not correct but don't want to deal with null pointer exception.
    	}
    }
    
    public boolean contains(String courseID){
    	int i = 0;
    	if (size == 0)
    		return false;
    	
     		CourseNode temp = head;
    		while(i != size)
	 		{
	 			if (temp.getCourse().getCourseID().equals(courseID)){
	 				return true;
	 			}

	 			i++;

	 		}
    		return false;
    	
    }
    
  
    public void getItemAt(int index)
	{
		if (index > size -1)
		{
			System.out.println("ERROR: Given index is out of range! Program will terminate. \n");
			throw new NoSuchElementException();
		}
		int i = 0;
		CourseNode temp = head;
		while(i != index)
		{
			temp = temp.next;
			i++;
		}
		
		Course tempC = temp.getCourse();
		System.out.println(tempC);
		System.out.println(head.getCourse());
		System.out.println(head.next.getCourse());
		System.out.println(head.next.next.getCourse());
		System.out.println(head.next.next.next.getCourse());
		System.out.println("<------------->");
	}

    public void showMe(){
    	
    	int i = 0;
		CourseNode temp = head;
		while(i != size)
		{
			System.out.println(temp.getCourse());
			temp = temp.next;
			i++;
		}
  
    }	
}
    
