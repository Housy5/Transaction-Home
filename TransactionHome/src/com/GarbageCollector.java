/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

/**

 @author Desktop
 */
public class GarbageCollector implements Runnable {

    private Thread thread;
    private boolean running;

    @Override
    public void run() {
        long lastTime = System.currentTimeMillis() / 1_000;
        while (running) {
            long elapsed = (System.currentTimeMillis() / 1_000) - lastTime;
            if (elapsed == 5) {
                Runtime.getRuntime().gc();
                lastTime = System.currentTimeMillis() / 1_000;
            }
        }
    }

    public void start() {
        if (running) {
            return;
        } else {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

}
