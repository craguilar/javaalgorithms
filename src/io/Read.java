package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Read {
    public Read() {
        super();
    }

    public static String read()
    {
        String cadena=null;
        try
        {
        InputStreamReader isr;
        BufferedReader br;
        isr=new InputStreamReader(System.in);
        br=new BufferedReader(isr);
        cadena=br.readLine();
        }
        catch(IOException e)
        {
         System.out.println("Error al leer del teclado..");
        }
        return cadena;
    }
    public static short readShort(String mensaje)
    {
        short corto=0;
        System.out.print(mensaje);
        try
        {
        corto=Short.parseShort(read());
        return corto;
        }
        catch(NumberFormatException e)
        {
         System.out.println("Error del formato..");
        }
        return corto;
    }
    public static long readLong(String mensaje)
    {
        long largo=0;
        System.out.print(mensaje);
        try
        {
        largo=Long.parseLong(read());
        }
        catch(NumberFormatException e)
        {
         System.out.println("Error del formato..");
        }
        return largo;
    }
     public static int readInt(String mensaje)
    {
        int entero=0;

        System.out.print(mensaje);
        try
        {
        entero=Integer.parseInt(read());
        }
        catch(NumberFormatException e)
        {
         System.out.println("Error del formato..");
        }
        return entero;
    }
    public static float readFloat(String mensaje)
    {
        float real=0;
        System.out.print(mensaje);
        try
        {
        real=Float.parseFloat(read());
        }
        catch(NumberFormatException e)
        {
         System.out.println("Error del formato..");
        }
        return real;
    }
     public static double readDouble(String mensaje)
    {
        double doble=0;
        System.out.print(mensaje);
        try
        {
        doble=Double.parseDouble(read());
        }
        catch(NumberFormatException e)
        {
         System.out.println("Error del formato..");
        }
        return doble;
    }
}

   