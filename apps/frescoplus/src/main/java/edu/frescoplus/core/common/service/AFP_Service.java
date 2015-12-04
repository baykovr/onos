package edu.frescoplus.core.common.service;

/**
 *
 */
public abstract class AFP_Service
{
        private boolean loaded;
        private boolean running;

        // Load but do not run the service yet. (it's not ready to process requests)
        public void load() {
                loaded = true;
        }

        // Run the service, it should now be ready to process requests.
        public void run() {
                running = true;
        }

        // Stop / Pause the service.
        public void stop() {
                running = false;
        }

        // Clean up and unload the service, save data to storage / release memory etc.
        public void unload() {
                loaded = false;
        }

        // Getters.
        public boolean loaded() {
                return loaded;
        }

        public boolean running() {
                return running;
        }

        // EventListeners
        // raise event
        // Processes Request
        // Process Event{
}
