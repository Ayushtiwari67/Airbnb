package com.airbnb.controller;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/phone")
public class PhoneController {

    @GetMapping("/parse")
    public String parsePhoneNumber(@RequestParam String number) {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            Phonenumber.PhoneNumber numberProto = phoneUtil.parse(number, "");
            return "Country code: " + numberProto.getCountryCode();
        } catch (NumberParseException e) {
            return "Error: Unable to parse the phone number. " + e.toString();
        }
    }
}
