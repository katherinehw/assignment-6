package herman;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.List;

public class LineCounter {
	
	File fi;
	int lines = 0;//needs to be outside of method
	
	public LineCounter(File fi) {
		this.fi = fi;
	}
	
	public int countLines() throws IOException{
		
		List<String> search = Files.readAllLines(Path.of(String.valueOf(this.fi)));//reads file
		int cnt = 0;
		
		for(String pass : search) {//for each string in file
			
			cnt++;//counts iterations of for loop
			
			if(pass.length() > 0) {//if the word is greater than a space
				
				for(int i = 1; i < pass.length(); i++) {//for loop to go through the chars in the indexed word
					
					if(pass.charAt(i) == '/' && pass.charAt(i+1) == '/') {break;}//determines if something is commented and will not count them
					else if(pass.charAt(i) == '*' && pass.charAt(i+1) == '*') {break;}//comment
					else if(pass.charAt(i) == '*' && pass.charAt(i+1) == '/') {//comment
						
						int indexer = search.indexOf(pass);//finds the index of the current word
						boolean checker = check(search.get(indexer+1));//gets the second word and sends to checker method
						
						if(!checker) {lines++;}//if it passes checker it will update the lines counter
						
						break;
						
					}else if(cnt == pass.length() - 1) {//adds line values if index matches the length of the word
						
						lines++;
						
					}
					
				}
				
			}
			
		}
		return lines;
	}
		
	//a checker method to make sure there isnt a corresponding commment mark
		private boolean check(String str) {
			
			for (int i=0; i<str.length(); i++) {
				
				if(str.charAt(i) == '*') {
					return true;
				}
				
			}
			return false;
		
		}
	
	

}
