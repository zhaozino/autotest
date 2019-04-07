package com.yunhailu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PluginBootstrap {

    private static final Logger logger = LoggerFactory.getLogger(PluginBootstrap.class);

    public List<AbstractClassEnhancePluginDefine> loadPlugins() {
        AgentClassLoader.initDefaultLoader();

        PluginResourcesResolver resolver = new PluginResourcesResolver();
        List<URL> resources = resolver.getResources();

        if (resources == null || resources.size() == 0) {
            logger.info("no plugin files (skywalking-plugin.def) found, continue to start application.");
            return new ArrayList<AbstractClassEnhancePluginDefine>();
        }

        for (URL pluginUrl : resources) {
            try {
                PluginCfg.INSTANCE.load(pluginUrl.openStream());
            } catch (Throwable t) {
                logger.error("plugin file [{}] init failure."+pluginUrl, t);
            }
        }

        List<PluginDefine> pluginClassList = PluginCfg.INSTANCE.getPluginClassList();

        List<AbstractClassEnhancePluginDefine> plugins = new ArrayList<AbstractClassEnhancePluginDefine>();
        for (PluginDefine pluginDefine : pluginClassList) {
            try {
                logger.debug("loading plugin class {}.", pluginDefine.getDefineClass());
                AbstractClassEnhancePluginDefine plugin =
                        (AbstractClassEnhancePluginDefine)Class.forName(pluginDefine.getDefineClass(),
                                true,
                                AgentClassLoader.getDefault())
                                .newInstance();
                plugins.add(plugin);
            } catch (Throwable t) {
                logger.error("load plugin [{}] failure."+pluginDefine.getDefineClass(), t);
            }
        }

        return plugins;

    }

}
