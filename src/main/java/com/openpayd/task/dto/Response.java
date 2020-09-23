package com.openpayd.task.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Response {
  public final static String SUCCESS_CODE = "100";
  @ApiModelProperty(notes = "Specifies result code of operation.", example = "100", required = true,
      position = 1)
  private String code;
  @ApiModelProperty(notes = "Specifies result message of operation.",
      example = "Operation is successful", required = true, position = 2)
  private String message;
  private String details;
  @ApiModelProperty(notes = "Specifies timestamp for notification operation ",
      example = "1547209842029", required = true, position = 3)
  private Date timestamp;


  public Response() {
    super();
    timestamp = new Date();
  }

  public Response(String code, String message, Date timestamp) {
    super();
    this.code = code;
    this.message = message;
    this.timestamp = timestamp;
  }


}
