// Copyright [2018] <mituh>

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Wget {

  public static void main(String[] args) {
    // read in data from URL
    String url = args[0];
    In in = new In(url);
    String data = in.readAll();

    // write data to the file
// System.out.printf("lastIndeOf = %d\n", url.lastIndexOf('/'));
    String filename = url.substring(url.lastIndexOf('/') + 1);
    Out out = new Out(filename);
    out.println(data);
    out.close();
  }
}

// java Wget https://introcs.cs.princeton.edu/data/codes.csv
