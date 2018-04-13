package com.example.sec.lec46_jsonparser3;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.example.sec.lec46_jsonparser3.Contact.ContactJsonVariables.*;
import static com.example.sec.lec46_jsonparser3.MainActivity.*;

public class Contact {
    interface ContactJsonVariables {
        String VAR_ID = "id";
        String VAR_NAME = "name";
        String VAR_PHONES = "phones"; // JSON 배열을 저장하기 위한 변수 이름
        String VAR_TYPE = "type";
        String VAR_PHONE_NO = "phoneno";
    }

    class Phone {
        private String type;
        private String phoneNo;

        public JSONObject toJsonObj() {
            JSONObject obj = new JSONObject();
            try {
                obj.put(VAR_TYPE, type);
                obj.put(VAR_PHONE_NO, phoneNo);
            } catch (JSONException e) {
                Log.e(TAG, e.getMessage());
            }
        }

        public Phone(String type, String phoneNo) {
            this.type = type;
            this.phoneNo = phoneNo;
        }

        public String getType() {
            return type;
        }

        public String getPhoneNo() {
            return phoneNo;
        }
    } // end class Phone

    private int id;
    private String name;
    private List<Phone> phones;

    public Contact(int id, String name, List<Phone> phones) {
        this.id = id;
        this.name = name;
        this.phones = phones;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    @Override
    public String toString() {
        return "아이디: " + id + "\n"
                + "이름: " + name + "\n"
                + "전화번호: " + phones;
    }

    public JSONObject toJsonObj() {
        JSONObject jsonObj = new JSONObject(); // {}
        try {
            jsonObj.put(VAR_ID, id); // {"id": id}
            jsonObj.put(VAR_NAME, name); // {"id": id, "name": name}

            // {"id": id, "name": name, "phones": [ ... ]}
            JSONArray jsonArray = new JSONArray(); // []
            for (Phone p : phones) {
                jsonArray.put(p.toJsonObj()); // [{}, {}, ...]
            }
            jsonObj.put(VAR_PHONES, jsonArray);

        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }

        return jsonObj;
    }
} // end class Contact









