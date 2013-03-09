import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.security.Credentials;
import com.hazelcast.security.ICredentialsFactory;
import com.hazelcast.security.UsernamePasswordCredentials;

import java.util.Properties;
import java.util.concurrent.BlockingQueue;

public class FullMember {
    public static void main(String[] args) throws Exception {
        Config config = new Config();

        LoginModuleConfig loginModuleConfig = new LoginModuleConfig();
        loginModuleConfig.setClassName("Foo");
        loginModuleConfig.setUsage(LoginModuleConfig.LoginModuleUsage.REQUIRED);

        SecurityConfig securityConfig = config.getSecurityConfig();
        securityConfig.setEnabled(true);
        securityConfig.addClientLoginModuleConfig(loginModuleConfig);

        HazelcastInstance hz = Hazelcast.newHazelcastInstance(config);

        BlockingQueue<String> queue = hz.getQueue("queue");
        for (; ; )
            System.out.println(queue.take());
    }
}
