#!/usr/bin/env bash

set -eo pipefail

# Check if AWS CLI is installed
if ! command -v aws &> /dev/null; then
    echo -e "\033[0;31minstalling awscli...\033[0m"
    brew install awscli
else
    echo -e "\033[0;32mawscli is already installed.\033[0m"
fi

# Check if direnv is installed
if ! command -v direnv &> /dev/null; then
    echo -e "\033[0;31minstalling direnv...\033[0m"
    brew install direnv
else
    echo -e "\033[0;32mdirenv is already installed.\033[0m"
fi

if [[ ! -f "$HOME/.zshrc" ]]; then
    touch "$HOME/.zshrc"
fi

# Add direnv hook to .zshrc if not present
if ! grep -q 'eval "$(direnv hook zsh)"' "$HOME/.zshrc"; then
    echo -e "\033[0;32mAdding direnv hook to .zshrc...\033[0m"
    echo 'eval "$(direnv hook zsh)"' >> "$HOME/.zshrc"

    # Source .zshrc to apply changes
    source "$HOME/.zshrc" 2> /dev/null || true
    # source "$HOME/.zshrc" 2> /dev/null
else
    echo -e "\033[0;32mdirenv hook is already present in .zshrc.\033[0m"
fi

echo "export AWS_PROFILE=ww" > .envrc
echo "export ENV=dev" >> .envrc

direnv allow

# Check if AWS profile 'ww' exists. if not, configure it
if ! aws configure list-profiles | grep -q '^ww$'; then
    echo -e "\033[0;32m'ww'에 대한 AWS profile을 설정해주세요.\033[0m"
    echo -e "\033[0;32mAPI KEY, SECRET은 알아서 설정하고 Region은 ap-northeast-2, Output은 json으로 설정하시면 됩니다.\033[0m"
    aws configure --profile ww
else
    echo -e "\033[0;32mAWS profile 'ww' already exists. no need to configure again.\033[0m"
fi

echo -e "\033[0;32mdone initializing awscli, direnv, and .zshrc\033[0m"
echo -e "\033[0;32mnow the default AWS_PROFILE is ww\033[0m"