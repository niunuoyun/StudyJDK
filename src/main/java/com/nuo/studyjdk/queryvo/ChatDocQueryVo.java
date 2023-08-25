package com.nuo.studyjdk.queryvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.ArrayUtils;

import java.util.*;

public class ChatDocQueryVo {
    String model = "chat-model-6B";
    private float frequency_penalty = 0;
    private float presence_penalty = 0;
    @JsonIgnore
    private Object stream;
    private int top_k = 20;
    private float top_p = 0.9f;
    private float   repetition_penalty = 0.9f;
    private float length_penalty;
    private float diversity_penalty;
    private int num_beams = 1;
    private int random_seed = 0;
    String skillId;

}

