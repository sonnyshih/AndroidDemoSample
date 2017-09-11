package com.example.demo.util;

import android.util.Log;

public class LoggerUtil {
    /**
     * Splits log content that is exceeds the buffer limit into smaller segments. Logs that are
     * split will have the tag &lt;LOG-CONT&gt; to indicated the log is a continuation of another
     * part. The message are assumed to be in ASCII characters
     * @param priority The priority of the logContent, such as Log.INFO, Log.Warning, Log.Verbose, etc.
     * @param tag The log's tag
     * @param content the content of the log.
     */
    public static void longLog(int priority,String tag, String content) {
        while (content.length() > 4000) {
            Log.println(priority, tag, content.substring(0, 4000));
            content = "<LOG-CONT>" + content.substring(4000);
        }
        Log.println(priority, tag, content);
    }
}
