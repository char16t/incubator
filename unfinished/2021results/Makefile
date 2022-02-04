up:
	cd ansible/vagrant && vagrant up

status:
	cd ansible/vagrant && vagrant status

halt:
	cd ansible/vagrant && vagrant halt

ssh:
	ssh -i ansible/vagrant/.vagrant/machines/default/virtualbox/private_key -l vagrant -p 2222 localhost

ping:
	cd ansible && ansible local -m ping

provision:
	cd ansible && ansible-playbook provision.yml
