package edu.android.lec42_xmlparser1;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import static edu.android.lec42_xmlparser1.MainActivity.TAG;

public class ContactXmlPullParser {
    // XML 파일에서 사용되는 TAG들의 이름을 상수로 정의
    private static final String TAG_PERSON = "person";
    private static final String TAG_NAME = "name";
    private static final String TAG_PHONE = "phone";

    // XML 파일을 분석해서 데이터를 저장할 ArrayList를 선언
    private List<Contact> contacts = new ArrayList<>();
    // XML 파일에서 한 사람의 연락처를 저장할 수 있는 데이터 타입을 선언
    private Contact contact;
    // XML 파일에서 시작 태그와 엔드 태그 사이의 문자열을 저장하는 변수 선언
    private String text;

    public List<Contact> getContacts(Reader reader)
            throws XmlPullParserException, IOException {
        // contacts.xml 파일을 분석해서 ArrayList를 만들고 리턴

        // 1) XmlPullParserFactory 인스턴스를 생성
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

        // 2) XmlPullParser 인스턴스를 생성
        XmlPullParser parser = factory.newPullParser();

        // 3) XmlPullParser 객체에게 XML 파일을 읽을 수 있는 Reader를 설정
        parser.setInput(reader);

        // 4) parser 객체를 가지고 XML 파일을 분석
        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            Log.i(TAG, "event type: " + eventType);
            String tagName = parser.getName(); // XML의 태그 이름을 리턴
            Log.i(TAG, "tag name: " + tagName);

            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    Log.i(TAG, "START DOCUMENT");
                    break;

                case XmlPullParser.START_TAG:
                    Log.i(TAG, "START TAG");
                    break;

                case XmlPullParser.TEXT:
                    text = parser.getText();
                    Log.i(TAG, "TEXT: " + text);

                    break;

                case XmlPullParser.END_TAG:
                    Log.i(TAG, "END TAG");
                    break;
            } // end switch

            eventType = parser.next();
        } // end while

        return contacts;
    } // end getContact()
}

