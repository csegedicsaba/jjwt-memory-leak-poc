package hu.cscs.poc;

import javax.crypto.SecretKey;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

public class Main {
    public static void main(String[] args) {

        System.out.printf("Start...\n");

        Runnable runnable = () -> {
            SecretKey key = Jwts.SIG.HS256.key().build();
            String jws = Jwts.builder().subject("JWT test").signWith(key).compressWith(Jwts.ZIP.GZIP).compact();

            JwtParser jp = Jwts.parser().verifyWith(key).build();

            for (int i = 0; i < 100_000; i++) {
                jp.parseSignedClaims(jws).getPayload().getSubject();
                if ((i % 10_000) == 0) {
                    System.out.printf("JWT verified: " + i + "\n");
                }
            }
        };

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(runnable);
            thread.start();
        }

        // System.out.printf("End...");

        try {
            Thread.sleep(5 * 60 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
