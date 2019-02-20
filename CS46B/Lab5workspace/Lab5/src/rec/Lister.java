package rec;

import java.io.File;

public class Lister {
	
	private File file;
	private boolean showHidden;
	
	public Lister (File file, boolean showHidden) {
		this.file = file;
		this.showHidden = showHidden;
	}
	
	private void listRecurse(File file, String indent) {
		
		System.out.print(indent + file.getName());
		
		if(file.isDirectory()) {
			System.out.println(":");
			
			for(File f : file.listFiles()) {
				if(f.isHidden()) {
					if(showHidden) {
						listRecurse(f, indent + "  ");
					}
				}else {
					if(!showHidden) {
						listRecurse(f, indent + "  ");
					}
				}
			}
		}
		else{
			System.out.println();
		}
	}
	
	public void list() {
		listRecurse(file, "");
	}

	public static void main (String[] args) {
		File testFile = new File ("/Users/iv.kitty/Desktop/CS46B/Eclipse/hw1_proj");
		Lister l  = new Lister(testFile, false);
		l.list();
	}
}

