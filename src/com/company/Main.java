package com.company;

import jdk.swing.interop.SwingInterOpUtils;
import org.w3c.dom.ls.LSOutput;

import java.util.NoSuchElementException;

public class Main {

    public static void main(String[] args) {
        try{
        Optional<String> optional = Optional.of("Sasha");
        System.out.println("Check a method: get()");
        System.out.println(optional.getData());
        //Optional<String> optional1 = Optional.of(null);
        // System.out.println(optional1.getData());
        Optional<String> optional2 = Optional.ofNullable(null);
        //  System.out.println(optional2.getData());
        Optional<String> optional3 = Optional.ofNullable("Dasha");
        System.out.println(optional3.getData());
        Optional<String> optional4 = Optional.of("Sasha");
        Optional<String> optional5 = Optional.of("10");
        Optional<Integer> optionalInt = Optional.of(5);
        System.out.println("________________________________________________________");
        System.out.println("Check a method: isPresent()");
        System.out.println(optional.isPresent());
        System.out.println(optional2.isPresent());
        System.out.println("________________________________________________________");
        System.out.println("Check a method: orElse()");
        System.out.println(optional2.orElse(optional3.getData()));
        System.out.println(optional.orElse(optional3.getData()));
        System.out.println("________________________________________________________");
        System.out.println("Check a method: equals()");
        System.out.println(optional.equals(optional3));
        System.out.println(optional.equals(optional4));
        System.out.println("________________________________________________________");
        System.out.println("Check a method: hashCode()");
        System.out.println(optional.getData() + ":  "+ optional.hashCode());
        System.out.println("hash code null "+ ":  "+optional2.hashCode());
        System.out.println(optional3.getData() + ":  "+optional3.hashCode());
        System.out.println(optional.getData() + ":  "+optional4.hashCode());
        System.out.println(optionalInt.getData() + ":  "+ optionalInt.hashCode());
        System.out.println("________________________________________________________");
        System.out.println("Check a method: ifPresent()");
        optional.ifPresent​(s -> System.out.println(optional3.getData()));
        System.out.println("________________________________________________________");
        System.out.println("Check a method: orElseGet()");
        System.out.println(optional2.orElseGet​(()-> optional4.getData()));
        System.out.println(optional3.orElseGet​(()-> optional4.getData()));
        System.out.println("________________________________________________________");
        System.out.println("Check a method: orElseThrow()");
        System.out.println(optional3.orElseThrow());
        //System.out.println(optional2.orElseThrow());
        System.out.println("________________________________________________________");
        System.out.println("Check a method: filter()");
        System.out.println(optional.filter​(o -> optional.getData().equals(Optional.of("Sasha").getData())).getData());
        System.out.println(optionalInt.filter​(o -> optionalInt.getData()>4).getData());
       // System.out.println(optionalInt.filter​(o -> optionalInt.getData()>20).getData());
        // System.out.println(optional.filter​(o -> optional.equals(optional2)).getData());
        System.out.println("________________________________________________________");
        System.out.println("Check a method: map()");
        System.out.println("Integer to String: " +(optionalInt.map​(s -> String.valueOf(optionalInt.getData())).getData()));
        System.out.println("String to Integer: " +(optional5.map​(i -> Integer.valueOf(optional5.getData())).getData()));
        System.out.println("________________________________________________________");
        System.out.println("Check a method: flatMap()");
        System.out.println((optional.flatMap(o -> optional3).getData()));
        System.out.println(optional.flatMap((o -> Optional.of("66"))).getData());}
        catch(NoSuchElementException | NullPointerException e) {
            System.out.println("Ошибка." + e);
        }
    }
}
