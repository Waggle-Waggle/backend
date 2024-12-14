#!/usr/bin/env bash

set -euo pipefail

# Check if ENV is set and valid
if [ -z ${ENV+x} ]; then
    echo -e "\033[0;31mENV 변수가 설정되어 있지 않습니다. 설정해주세요.\033[0m"
    echo -e "\033[0;31m> export ENV=dev\033[0m"
    exit 1
fi

if [[ ! "$ENV" =~ ^(local|dev|stag|qa|prod)$ ]]; then
    echo -e "\033[0;31mENV 변수는 local, dev, stag, qa, prod 중 하나여야 합니다. 현재 값: $ENV\033[0m"
    exit 1
fi

# Check if AWS CLI is installed
if ! command -v aws &> /dev/null
then
    echo -e "\033[0;31mAWS CLI가 설치되어 있지 않습니다. 먼저 설치 해주세요\033[0m"
    echo -e "\033[0;31m> brew install awscli\033[0m"
    exit 1
fi

# Set the AWS region
AWS_REGION="ap-northeast-2"
BUCKET_NAME="ww-shared-apne2-env-bucket"
# BUCKET_NAME="ww-dev-apne2-imgs"
FILE_PATH=".env.$ENV"
S3_KEY=".env.$ENV"


# Upload the env file to S3
upload_file() {
    if [[ ! -f "$FILE_PATH" ]]; then
        echo -e "\033[0;31m$FILE_PATH 파일이 현재 루트 디렉토리에 존재하지 않습니다.\033[0m"
        echo -e "\033[0;31m업로드에 실패 하였습니다.\033[0m"
        exit 1
    fi
    echo -e "\033[0;34mUploading $FILE_PATH to s3://$BUCKET_NAME/$S3_KEY\033[0m"
    aws s3 cp "$FILE_PATH" "s3://$BUCKET_NAME/$S3_KEY" --region "$AWS_REGION"
}

# Download the env file from S3
download_file() {
    echo -e "\033[0;34mDownloading s3://$BUCKET_NAME/$S3_KEY to $FILE_PATH\033[0m"
    aws s3 cp "s3://$BUCKET_NAME/$S3_KEY" "$FILE_PATH" --region "$AWS_REGION"
}

# Check the first argument to determine whether to upload or download
if [ "$#" -ne 1 ]; then
    # echo -e "\033[0;31mUsage: $0 {up|down}\033[0m"
    exit 1
fi

case $1 in
    up)
        upload_file
        ;;
    down)
        download_file
        ;;
    *)
        echo -e "\033[0;31m'make env up' | 'make env down' 형식으로 입력해주세요.\033[0m"
        exit 1
        ;;
esac
