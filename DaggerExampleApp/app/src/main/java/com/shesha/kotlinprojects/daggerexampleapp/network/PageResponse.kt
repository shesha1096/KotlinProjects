package com.shesha.kotlinprojects.daggerexampleapp.network
// version 2.11.1
// version 2.11.1
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper

/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString), Root.class); */
class PageResponse {
    var id = 0
    var date: String? = null
    var date_gmt: String? = null
    var modified: String? = null
    var modified_gmt: String? = null
    var slug: String? = null
    var status: String? = null
    var type: String? = null
    var link: String? = null
    var excerpt: Excerpt? = null
    var author = 0
    var featured_media = 0
    var parent = 0
    var menu_order = 0
    var comment_status: String? = null
    var ping_status: String? = null
    var template: String? = null
    var meta: Meta? = null
    private var _links: Links? = null

    inner class Excerpt {
        var rendered: String? = null
    }

    inner class Meta {
        var spay_email: String? = null
        var _links_to: String? = null
        var _links_to_target: String? = null
    }

    inner class Self {
        var href: String? = null
    }

    inner class Collection {
        var href: String? = null
    }

    inner class About {
        var href: String? = null
    }

    inner class Author {
        var embeddable = false
        var href: String? = null
    }

    inner class Reply {
        var embeddable = false
        var href: String? = null
    }

    inner class VersionHistory {
        var count = 0
        var href: String? = null
    }

    inner class PredecessorVersion {
        var id = 0
        var href: String? = null
    }

    inner class WpAttachment {
        var href: String? = null
    }

    inner class Cury {
        var name: String? = null
        var href: String? = null
        var templated = false
    }

    inner class WpFeaturedmedia {
        var embeddable = false
        var href: String? = null
    }

    inner class Links {
        var self: List<Self>? = null
        var collection: List<Collection>? =
            null
        var about: List<About>? = null
        var author: List<Author>? = null
        var replies: List<Reply>? = null

        @JsonProperty("version-history")
        var versionHistory: List<VersionHistory>? = null

        @JsonProperty("predecessor-version")
        var predecessorVersion: List<PredecessorVersion>? = null

        @JsonProperty("wp:attachment")
        var wpAttachment: List<WpAttachment>? = null
        var curies: List<Cury>? = null

        @JsonProperty("wp:featuredmedia")
        var wpFeaturedmedia: List<WpFeaturedmedia>? = null
    }

    fun get_links(): Links? {
        return _links
    }

    fun set_links(_links: Links?) {
        this._links = _links
    }
}