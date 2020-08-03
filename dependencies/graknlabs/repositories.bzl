#
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#   http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
#

load("@bazel_tools//tools/build_defs/repo:git.bzl", "git_repository")

def graknlabs_graql():
    git_repository(
        name = "graknlabs_graql",
        remote = "https://github.com/graknlabs/graql",
        commit = "4aa5ae0be36ad23a9092efd85edf9037a6fbc0c1" # sync-marker: do not remove this comment, this is used for sync-dependencies by @graknlabs_graql
    )

def graknlabs_common():
#    git_repository(
#        name = "graknlabs_common",
#        remote = "https://github.com/graknlabs/common",
#        commit = "f4e43ee40d92b0a124bc6eff5c22c7ce1cb081b6" # sync-marker: do not remove this comment, this is used for sync-dependencies by @graknlabs_common
#    )
    native.local_repository(
        name = "graknlabs_common",
        path = "../common",
    )

def graknlabs_dependencies():
    git_repository(
        name = "graknlabs_dependencies",
        remote = "https://github.com/graknlabs/dependencies",
        commit = "504561c1343b12cfb08cd65c25155045da0b709e", # sync-marker: do not remove this comment, this is used for sync-dependencies by @graknlabs_dependencies
    )

def graknlabs_protocol():
    git_repository(
        name = "graknlabs_protocol",
        remote = "https://github.com/graknlabs/protocol",
        commit = "9f98160d1b3cbb2eac5d6cef5ea9a986cdb4d4a2", # sync-marker: do not remove this comment, this is used for sync-dependencies by @graknlabs_protocol
    )

def graknlabs_verification():
#    git_repository(
#        name = "graknlabs_verification",
#        remote = "https://github.com/graknlabs/verification",
#        commit = "bc79a81694afb855797e06a15cf56fb6ff288326", # sync-marker: do not remove this comment, this is used for sync-dependencies by @graknlabs_verification
#    )
    native.local_repository(
        name = "graknlabs_verification",
        path = "../verification",
    )

def graknlabs_grabl_tracing():
    git_repository(
        name = "graknlabs_grabl_tracing",
        remote = "https://github.com/graknlabs/grabl-tracing",
        commit = "242dd019f229790732dec35ac34064601b03c363"  # sync-marker: do not remove this comment, this is used for sync-dependencies by @graknlabs_grabl_tracing
    )
