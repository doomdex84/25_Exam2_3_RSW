package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public Scanner sc;
    public App(Scanner sc) {
        this.sc = sc;
    }

    public void run() {

        System.out.println("모티 실행");

        int lastId = 0;
        List<Motivation>motivations = new ArrayList<Motivation>();

        while (true) {
            System.out.print("명령어 : ");
            String cmd = sc.nextLine().trim();

            if (cmd.equals("exit")) {
                System.out.println("모티 종료");
                break;

            } else if (cmd.equals("add")) {
                int id = lastId + 1;
                System.out.print("body : ");
                String body = sc.nextLine();
                System.out.print("source : ");
                String source = sc.nextLine();
                System.out.println(id + "번 모티가 저장됨");
                lastId++;

                Motivation motivation = new Motivation(id, body, source);
                motivations.add(motivation);
            } else if (cmd.equals("list")) {
                if (motivations.isEmpty()) {
                    System.out.println("해당 모티는 없어");
                }
                System.out.println("=".repeat(40));
                System.out.println("   번호   /   제목   /   내용   ");

                for (int i = motivations.size() - 1; i >= 0; i--) {
                    Motivation motivation = motivations.get(i);
                    System.out.printf("   %d   /   %s   /   %s   \n", motivation.getId(), motivation.getBody(), motivation.getSource());
                }
                System.out.println("=".repeat(40));
            }

            else if (cmd.startsWith("delete")) {

                String[] cmdBits = cmd.split(" ");
                if (cmdBits.length < 2) {
                    System.out.println("삭제할 번호를 입력해");
                    continue;
                }
                try {
                    int id = Integer.parseInt(cmdBits[1]);
                    Motivation foundmotivation = null;

                    for (Motivation motivation : motivations) {
                        if (motivation.getId() == id) {
                            foundmotivation = motivation;
                            break;
                        }
                        if (motivation == null) {
                                System.out.println("해당 모티는 없어");
                                continue;
                           } else {
                               motivations.remove(foundmotivation);
                            System.out.println(id + "번 모티를 삭제합니다.");
                            break;
                            }
                        }
                } catch (NumberFormatException e) {
                    System.out.println("올바른 번호를 입력해");
                }
            }

            else if (cmd.startsWith("edit")) {
                String[] cmdBits = cmd.split(" ");
                if (cmdBits.length < 2) {
                    System.out.println("수정할 번호를 입력해");
                    continue;
                }
                int id;
                try{
                    id = Integer.parseInt(cmdBits[1]);
                }catch(NumberFormatException e){
                    System.out.println("올바른 번호를 입력해");
                    continue;
                }

                Motivation motivation = findById(motivations,id);

                if (motivation == null) {
                    System.out.println("해당 모티는 없어");
                    continue;
                }

                String newBody;
                String newSource;

                while (true){
                    System.out.print("new body : ");
                    newBody = sc.nextLine();
                    if(!newBody.isEmpty())break;
                    System.out.print("수정할  body가 없어");
                }
                while (true){
                    System.out.print("new source : ");
                    newSource = sc.nextLine();
                    if(!newSource.isEmpty())break;
                    System.out.print("수정할  source가 없어");
                }

                motivation.setBody(newBody);
                motivation.setSource(newSource);
                System.out.println(id+"번 모티를 수정합니다.");

            } else{
                System.out.println("올바른 명령어를 입력해");
            }
        }
}
private Motivation findById(List<Motivation> motivations, int id) {
    for (Motivation motivation : motivations) {
        if (motivation.getId() == id) {
            return motivation;
        }
    }
    return null;
    }
}
class Motivation {
    private int id;
    private String body;
    private String source;

    public Motivation(int id, String body, String source) {
        this.id = id;
        this.body = body;
        this.source = source;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}