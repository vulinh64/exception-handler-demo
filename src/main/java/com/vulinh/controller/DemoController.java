package com.vulinh.controller;

import module java.base;

import com.vulinh.data.DemoRequest;
import com.vulinh.data.DemoResponse;
import com.vulinh.data.ResponseObject;
import com.vulinh.exception.IdenticalException;
import com.vulinh.exception.NotFound401Exception;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class DemoController {

  private static final Map<String, Instant> DUMMY_DATA = new ConcurrentHashMap<>();

  @PostMapping("/create")
  public ResponseObject<DemoResponse> create(@RequestBody DemoRequest demoRequest) {
    // NullPointerException will be thrown here if name is null
    // Perfect for demonstrating our global exception handler
    var uppercaseKey = demoRequest.name().toUpperCase();

    if (DUMMY_DATA.containsKey(uppercaseKey)) {
      throw new IdenticalException(demoRequest.name());
    }

    DUMMY_DATA.put(uppercaseKey, Instant.now());

    return ResponseObject.of(
        DemoResponse.builder().name(uppercaseKey).timestamp(DUMMY_DATA.get(uppercaseKey)).build());
  }

  @GetMapping("/get")
  public ResponseObject<DemoResponse> get(@RequestParam("name") String base64Name) {
    var name = parseBase64(base64Name);

    var uppercaseKey = name.toUpperCase();

    if (!DUMMY_DATA.containsKey(uppercaseKey)) {
      throw new NotFound401Exception(name);
    }

    return ResponseObject.of(
        DemoResponse.builder().name(name).timestamp(DUMMY_DATA.get(uppercaseKey)).build());
  }

  // IllegalArgumentException may be thrown here
  private static String parseBase64(String base64Name) {
    return new String(Base64.getDecoder().decode(base64Name), StandardCharsets.UTF_8);
  }
}
