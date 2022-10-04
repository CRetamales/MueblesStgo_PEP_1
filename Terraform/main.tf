terraform {
  required_providers {
    digitalocean = {
      source = "digitalocean/digitalocean"
      version = "~> 2.0"
    }
  }
}

# Set the variable value in *.tfvars file
# or using -var="do_token=..." CLI option
variable "do_token" {}

#Data de digital ocean con el nombre del proyecto
data "digitalocean_project" "MueblesStgo" {
  name = "MueblesStgo"
}


#Region del droplet
variable "region" {
  type= string
  default = "sfo3"
}

#Caracteristicas
variable "droplet_size" {
  type = string
  default = "s-1vcpu-1gb"
}

# Configure the DigitalOcean Provider
provider "digitalocean" {
  token = var.do_token
}

data "digitalocean_ssh_key" "mysshkey" {
  name = "Home-Windows"
}

resource "digitalocean_droplet" "web" {
  image  = "ubuntu-20-04-x64"
  name   = "MueblesStgo-web-${var.region}-${var.droplet_size}"
  region = var.region
  size   = var.droplet_size
  ssh_keys = [data.digitalocean_ssh_key.mysshkey.id]

  lifecycle {
    create_before_destroy = true
  }
}


#Utiliza los recursos para mandar al proyecto los
#recursos
resource "digitalocean_project_resources" "dev" {
  project = data.digitalocean_project.MueblesStgo.id
  resources = [digitalocean_droplet.web.urn]
}



output "server_ip" {
  value = digitalocean_droplet.web.*.ipv4_address
}