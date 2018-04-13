package edu.android.lec45_jsonparser2;

import org.json.JSONException;
import org.json.JSONObject;

import static edu.android.lec45_jsonparser2.Contact.ContactJsonVariables.*;

public class Contact {
    // JSON 객체에서 사용할 변수 이름들을 내부 인터페이스에서 상수로 정의
    public interface ContactJsonVariables {
        String VAR_ID = "id";
        String VAR_NAME = "name";
        String VAR_PHONE = "phone";
    }

    // Contact 클래스의 멤버 변수
    private int id;
    private String name;
    private String phone;

    public Contact(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "\t아이디: " + id + "\n"
                + "\t이름: " + name + "\n"
                + "\t전화번호: " + phone;
    }

    // Contact 인스턴스를 JSONObject로 변환해서 리턴하는 메소드
    public JSONObject toJsonObj() {
        JSONObject jsonObj = new JSONObject(); // ()
        try {
            jsonObj.put(VAR_ID, id); // ("id": id)
            jsonObj.put(VAR_NAME, name); // ("id": id."name": name)
            jsonObj.put(VAR_PHONE, phone);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObj;
    }
}













