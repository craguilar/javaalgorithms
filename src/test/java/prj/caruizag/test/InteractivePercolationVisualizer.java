package prj.caruizag.test;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import prj.caruizag.algorithms.percolation.Percolation;

public class InteractivePercolationVisualizer {

  public static void main(String[] args) {
    // n-by-n percolation system (read from command-line, default = 10)
    int n = 10;
    if (args.length == 1)
      n = Integer.parseInt(args[0]);

    // repeatedly open site specified my mouse click and draw resulting system
    StdOut.println(n);

    StdDraw.enableDoubleBuffering();
    Percolation perc = new Percolation(n);
    PercolationVisualizer.draw(perc, n);
    StdDraw.show();

    while (true) {

      // detected mouse click
      if (StdDraw.mousePressed()) {

        // screen coordinates
        double x = StdDraw.mouseX();
        double y = StdDraw.mouseY();

        // convert to row i, column j
        int i = (int) (n - Math.floor(y));
        int j = (int) (1 + Math.floor(x));

        // open site (i, j) provided it's in bounds
        if (i >= 1 && i <= n && j >= 1 && j <= n) {
          if (!perc.isOpen(i, j)) {
            StdOut.println(i + " " + j);
          }
          perc.open(i, j);
        }
        System.out.println(perc.isFull(3, 1));
        // draw n-by-n percolation system
        PercolationVisualizer.draw(perc, n);
        StdDraw.show();
      }

      StdDraw.pause(20);
    }
  }
}
