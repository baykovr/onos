#
# 
target=target/frescoplus-1.4.0-SNAPSHOT.oar

all:
	mvn clean install

install:
	onos-app 127.0.0.1 install! $(target)

reinstall:
	onos-app 127.0.0.1 reinstall! $(target)

uninstall:
	onos-app localhost uninstall edu.frescoplus.onos.FP_ONOSRTE

clean:
	rm $(target)
	rm -rf ~/Applications/apps/edu.frescoplus.onos.FP_ONOSRTE

# Some settings bleed into karaf and I don't know how to prune them
# this is a drastic solution.
vclean:
	rm -rf ~/Applications 
